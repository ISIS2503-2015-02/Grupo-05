package co.edu.uniandes.csw.auth.service;

import co.edu.uniandes.csw.auth.model.UserDTO;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountCriteria;
import com.stormpath.sdk.account.AccountList;
import com.stormpath.sdk.account.AccountStatus;
import com.stormpath.sdk.account.Accounts;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.group.GroupList;
import com.stormpath.sdk.oauth.AccessTokenResult;
import com.stormpath.sdk.resource.ResourceException;
import com.stormpath.shiro.realm.ApplicationRealm;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthService {

    @Path("/login")
    @POST
    public Response login(UserDTO user) {
        System.out.println("Autenticando...");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword(), user.isRememberMe());
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);

            Map<String, String> userAttributes = (Map<String, String>) currentUser.getPrincipals().oneByType(java.util.Map.class
            );
            user.setName(userAttributes.get("givenName") + " " + userAttributes.get("surname"));
            user.setEmail(userAttributes.get("email"));
            user.setUserName(userAttributes.get("username"));
            String role = "Estacion";
            if (currentUser.hasRole(role));
            if (currentUser.hasRole("Vehiculo")) {
                role = "Vehiculo";
            }
            if (currentUser.hasRole("Cliente")) {
                role = "Cliente";
            }

            if (currentUser.hasRole("Admin")) {
                role = "Admin";
            }
            user.setRole(role);
            return Response.ok(user)
                    .build();
        } catch (AuthenticationException e) {
            Logger.getLogger(AuthService.class.getName()).log(Level.WARNING, e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @Path("/logout")
    @GET
    public Response logout() {
        System.out.println("Saliendo...");
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            currentUser.logout();
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/currentUser")
    @GET
    public Response getCurrentUser() {
        UserDTO user = new UserDTO();
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser != null) {
            Map<String, String> userAttributes = (Map<String, String>) currentUser.getPrincipals().oneByType(java.util.Map.class
            );
            user.setName(userAttributes.get("givenName") + " " + userAttributes.get("surname"));
            user.setEmail(userAttributes.get("email"));
            user.setUserName(userAttributes.get("username"));
            String role = "Estacion";
            if (currentUser.hasRole(role)); else if (currentUser.hasRole("Vehiculo")) {
                role = "Vehiculo";
            } else {
                role = "Cliente";
            }
            user.setRole(role);
            return Response.ok(user)
                    .build();
        } else {
            Logger.getLogger(AuthService.class.getName()).log(Level.WARNING, "user null");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("user null")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @Path("/register")
    @POST
    public Response setUser(UserDTO user) {
        try {
            createUser(user);
            return Response.ok().build();
        } catch (ResourceException e) {
            return Response.status(e.getStatus())
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    public static Account createUser(UserDTO user) {
        Account acct = getClient().instantiate(Account.class);

        acct.setUsername(user.getUserName());
        acct.setPassword(user.getPassword());
        acct.setEmail(user.getEmail());
        acct.setGivenName(user.getName());
        acct.setStatus(AccountStatus.ENABLED);

        Application application = getApplication();
        GroupList groups = application.getGroups();
        for (Group grp : groups) {
            if (grp.getName().equals(user.getRole())) {
                acct = application.createAccount(acct);
                acct.addGroup(grp);
                break;
            }
        }
        return acct;
    }

    @Path("delete/{username}")
    @DELETE
    public void deleteAccount(@PathParam("username") String username) {
        AccountCriteria criteria = Accounts.where(Accounts.username().eqIgnoreCase(username));
        AccountList accounts = getApplication().getAccounts(criteria);
        for (Account account : accounts) {
            account.delete();
        }
    }

    private static ApplicationRealm getRealm() {
        return ((ApplicationRealm) ((RealmSecurityManager) SecurityUtils.getSecurityManager()).getRealms().iterator().next());
    }

    private static Client getClient() {
        return getRealm().getClient();
    }

    private static Application getApplication() {
        return getClient().getResource(getRealm().getApplicationRestUrl(), Application.class);
    }

    @OPTIONS
    @Path("{path : .*}")
    public Response options() {
        System.out.println("Llego un options...");
        return Response.ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }
}

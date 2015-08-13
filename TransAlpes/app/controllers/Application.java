package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.*;

import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import models.Person;
import play.data.Form;
import java.util.List;

import static play.libs.Json.*;

public class Application extends Controller
{

    public Result index() {
        return ok(index.render());
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result addPerson() {
        JsonNode json = request().body().asJson();

        Person person = Json.fromJson(json, Person.class);
        System.out.println("Agregando a: "+person.name);
        person.save();
        return redirect(routes.Application.index());
    }

    @Transactional
    public Result getPersons() {
        List<Person> persons = Person.find.all();
        System.out.println("Obteniendo personas..." + toJson(persons));
        return ok(toJson(persons));
    }

    @Transactional
    public Result deletePersons()
    {
        List<Person> persons = Person.find.all();
        System.out.println("Eliminando las personas");
        for(Person p: persons)
            p.delete();
        return redirect(routes.Application.index());
    }
}

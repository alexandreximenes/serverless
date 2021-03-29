package basics;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    public String helloWorld(String name){

        String repeat = name.repeat(10);
        String repeat2 =
        "SELECT * FROM USUARIO U "+
        "WHERE U.ID > 100 ".repeat(3);
        System.out.println(repeat2);
        return "Hello world "+repeat;
    }


    public double mDouble(float n){
        System.out.println(n);
        return n+n;
    }

    public List<String> listNames(List<Integer> ids){
        System.out.println(ids);

        Map<Integer, String> names = new HashMap<>();
        names.put(1, "Alexandre");
        names.put(2, "Tiago");
        names.put(3, "Ximenes");


       return names.entrySet()
               .stream()
               .filter(k1 -> ids.contains(k1.getKey()))
               .map(k2 -> k2.getValue())
               .collect(Collectors.toList());
    }

    public String typeJson(Map<String, Integer> map){

        System.out.println(map);

        return map.toString();

    }

    public Map<String, List<Integer>> strinAndArray(){

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("Alexandre", List.of(1,2,3));
        map.put("Day", List.of(8));
        map.put("Arthur", List.of(5));

        return map;

    }

    public String getPersons() throws Exception{

        Hobby ler = new Hobby();
        ler.setId(1l);
        ler.setName("Ler");

        Hobby escrever = new Hobby();
        escrever.setId(1l);
        escrever.setName("Ler");

        Person p = new Person();
        p.setId(1L);
        p.setName("Alex");
        p.setHobbies(List.of(ler, escrever));


        Person p2 = new Person();
        p2.setId(1L);
        p2.setName("Amanda");
        p2.setHobbies(List.of(ler, escrever));

        Person p3 = new Person();
        p3.setId(1L);
        p3.setName("Day");
        p3.setHobbies(List.of(ler, escrever));


        String strReturn = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(List.of(p, p2, p3));
        System.out.println(strReturn);

        return strReturn;

    }


    public String getPersons(Context context) throws Exception{

        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(context);
        System.out.println(s);

        s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(System.getenv());
        System.out.println(s);

        return "OK";

    }


}

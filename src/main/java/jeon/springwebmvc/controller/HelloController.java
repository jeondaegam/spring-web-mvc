package jeon.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model) {
        model.addAttribute("name", "Jeon");
        return "hello";
    }

    /**
     * MVC 방식
     *
     * @param name
     * @param model
     * @return
     */
    @GetMapping("hello-mvc")
    public String HelloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-mvc"; // view resolver가 hello-mvc.html을 찾는다!
    }

    /**
     * API 방식
     * @ResponseBody를 선언하고 객체를 반환하면 객체가 JSON 타입으로 반환된다.
     *
     *
     */
    @GetMapping("hello-api")
    @ResponseBody
    public User helloApi(@RequestParam("name") String name) {
        User user = new User();
        user.setName(name);
        return user;
    }


    /**
     * gettet, setter가 뭔가요?
     *
     * Java bean규약이라고 한다.
     * private 변수를 public 메서드를 통해 접근하는 방식
     * 프로퍼티 방식이라고도 함
     */
    static class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


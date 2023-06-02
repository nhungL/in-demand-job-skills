//package ca.myapp.controllers;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.util.StreamUtils;
//import java.nio.charset.StandardCharsets;
//
//import org.springframework.ui.Model;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//public class RouteController {
//    @GetMapping("/hello")
//    public String getHelloMessage() {
//        return "Hello World, from Spring!";
//    }
//
//    @GetMapping("/python")
//    public String index(Model model) throws IOException {
//        // Run the Python script
//        Process process = Runtime.getRuntime().exec("python3 python/scripts/my_scripts.py");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        StringBuilder result = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            result.append(line);
//        }
//
//        // Pass the result to the HTML template
//        model.addAttribute("scriptResult", result.toString());
//        return result.toString();
//    }
//
//    @GetMapping("/")
//    public String index() throws IOException {
//        Resource resource = new ClassPathResource("static/index.html");
//        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
//    }
//}

package movie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product/")
public class ProductController {
    @GetMapping("banana")
    public String banana() {
        return "Day la qua chuoi";
    }

    @GetMapping("apple")
    public String apple() {
        return "Day la qua tao";
    }

    @GetMapping("fish")
    public String fish() {
        return "Day la con ca";
    }

    @GetMapping("sion")
    public String sion() {
        return "Day la con su tu";
    }
}

package mozt.springframework.spring6restmvc.controllers;

import lombok.AllArgsConstructor;
import mozt.springframework.spring6restmvc.services.BeverageService;
import org.springframework.stereotype.Controller;
@AllArgsConstructor
@Controller
public class BeverageController {
    private final BeverageService bs;

}

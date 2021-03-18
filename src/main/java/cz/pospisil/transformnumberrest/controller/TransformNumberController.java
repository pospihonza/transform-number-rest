package cz.pospisil.transformnumberrest.controller;

import cz.pospisil.transformnumberrest.domain.TransformedNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransformNumberController {

    @GetMapping("/transformnumber")
    public TransformedNumber transformedNumber(@RequestParam(value = "number") String number) {
        return new TransformedNumber(number);
    }
}

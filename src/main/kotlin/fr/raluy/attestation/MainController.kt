package fr.raluy.attestation

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MainController {

    @GetMapping()
    @ResponseBody
    public fun index(): String {
        return "test"
    }
}

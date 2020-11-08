package fr.raluy.attestation

import org.jsoup.Jsoup
import org.jsoup.nodes.DataNode
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine


@Controller
class MainController(val templateEngine: SpringTemplateEngine) {

    @GetMapping("/")
    @ResponseBody
    fun mirrorRest(
            @RequestParam prenom: String,
            @RequestParam nom: String,
            @RequestParam dateNaissance: String,
            @RequestParam lieuNaissance: String,
            @RequestParam adresse: String,
            @RequestParam ville: String,
            @RequestParam codePostal: String,
            @RequestParam minutesDepart: Int,
            @RequestParam motifs: String
    )
            : String {
        val context = createContext(prenom, nom, dateNaissance, lieuNaissance, adresse, ville, codePostal, minutesDepart, motifs)
        val process = templateEngine.process("script.js", context)

        val doc = Jsoup.connect("https://media.interieur.gouv.fr/deplacement-covid-19/").get()

        val indexPage = templateEngine.process("/index.html", Context())
        var elt = Jsoup.parse(indexPage)
        elt.body().getElementById("container").append(doc.body().html())
        elt.body().getElementById("container").appendElement("script")
                .attr("type", "text/javascript")
                .appendChild(DataNode(process, ""))

        return elt.toString()
    }

    private fun createContext(prenom: String, nom: String, dateNaissance: String, lieuNaissance: String, adresse: String, ville: String, codePostal: String, minutesDepart: Int, motifs: String): Context {
        val context = Context()
        context.setVariable("prenom", prenom)
        context.setVariable("nom", nom)
        context.setVariable("dateNaissance", dateNaissance)
        context.setVariable("lieuNaissance", lieuNaissance)
        context.setVariable("adresse", adresse)
        context.setVariable("ville", ville)
        context.setVariable("codePostal", codePostal)
        context.setVariable("minutesDepart", minutesDepart)
        context.setVariable("motifs", motifs)
        return context
    }

}

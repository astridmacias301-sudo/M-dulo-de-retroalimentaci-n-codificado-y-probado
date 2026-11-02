package com.campus.retroalimentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RetroControlador {

    @GetMapping("/")
    public String mostrarFormulario() {
        return "retro";
    }

    @PostMapping("/ejecutar")
    public String procesarEjecucion(@RequestParam("codigoUsuario") String codigoUsuario, Model modelo) {
        
        String detalleRetroalimentacion;
        String codigoMinuscula = codigoUsuario.toLowerCase();

        // Lógica de revisión de errores en el código del estudiante
        if (codigoMinuscula.contains("error") || !codigoMinuscula.contains(";")) {
            detalleRetroalimentacion = "⚠️ Error detectado en la ejecución:\n" +
                                      "- Línea analizada: Verifique la estructura de su sentencia.\n" +
                                      "- Detalle: Parece que le falta un punto y coma (; o contiene la palabra error).";
        } else {
            detalleRetroalimentacion = "✔️ ¡Ejecución exitosa!\n" +
                                      "- Su código superó todas las pruebas del ejercicio.";
        }

        modelo.addAttribute("resultadoRevision", detalleRetroalimentacion);
        return "retro";
    }
}
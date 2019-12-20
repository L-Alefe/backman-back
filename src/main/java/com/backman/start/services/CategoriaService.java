package com.backman.start.services;

import com.backman.start.models.CategoriaModel;
import com.backman.start.controlers.CategoriaController;
import org.springframework.web.bind.annotation.*;
import com.backman.start.models.CategoriaModel;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CategoriaService {
    CategoriaController categoriaCon = new CategoriaController();

    @RequestMapping(value = "/categorias", method = GET)
    @ResponseBody
    public ArrayList getCategoria() {
        return categoriaCon.selecionaCategoria();
    }

    @RequestMapping(value = "/categorias/{id}", method = GET)
    @ResponseBody
    public ArrayList getCategoriaPorId(@PathVariable String id) {
        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(Integer.parseInt(id));
        return categoriaCon.selecionaCategoriaPorId(categoria);
    }

    @RequestMapping(value = "/categorias/excluir/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteCategoriaPorId(@PathVariable String id) {
        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(Integer.parseInt(id));
        return categoriaCon.deletaCategoriaPorId(categoria);
    }

    @PostMapping(value = "/categorias/inserir")
    @ResponseBody
    public boolean inseriCategoria(@RequestBody CategoriaModel categoria) {
        return categoriaCon.inseriCategoria(categoria);
    }

    @PutMapping("/categorias/update")
    public Boolean updateCategoria(@RequestBody CategoriaModel categoria) {
        return categoriaCon.updateCategoria(categoria);
    }
}

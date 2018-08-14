package com.javaee.luizpassos.webstockmarket.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.luizpassos.webstockmarket.api.v1.model.CompradorDTO;
import com.javaee.luizpassos.webstockmarket.services.CompradorService;


@RestController
@RequestMapping(CompradorController.BASE_URL)
public class CompradorController {
	public static final String BASE_URL = "/api/v1/acionistas";

    private final CompradorService compradorService;

    public CompradorController(CompradorService compradorService) {
        this.compradorService = compradorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompradorDTO> getAll(){
        return compradorService.getAll();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CompradorDTO getById(@PathVariable Long id){
        return compradorService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompradorDTO createNew(@RequestBody CompradorDTO comprador){
        return compradorService.createNew(comprador);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CompradorDTO update(@PathVariable Long id, @RequestBody CompradorDTO comprador){
        return compradorService.save(id, comprador);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
    	compradorService.deleteById(id);
    }
}

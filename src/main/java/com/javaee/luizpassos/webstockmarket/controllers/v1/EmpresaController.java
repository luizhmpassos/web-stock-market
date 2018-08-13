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

import com.javaee.luizpassos.webstockmarket.api.v1.model.EmpresaDTO;
import com.javaee.luizpassos.webstockmarket.services.EmpresaService;

@RestController
@RequestMapping(EmpresaController.BASE_URL)
public class EmpresaController {

	public static final String BASE_URL = "/api/v1/empresas";

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmpresaDTO> getAll(){
        return empresaService.getAll();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EmpresaDTO getById(@PathVariable Long id){
        return empresaService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaDTO createNew(@RequestBody EmpresaDTO empresa){
        return empresaService.createNew(empresa);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public EmpresaDTO update(@PathVariable Long id, @RequestBody EmpresaDTO empresa){
        return empresaService.save(id, empresa);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
    	empresaService.deleteById(id);
    }
	
}

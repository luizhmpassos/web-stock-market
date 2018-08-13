package com.javaee.luizpassos.webstockmarket.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.luizpassos.webstockmarket.api.v1.model.AcaoDTO;
import com.javaee.luizpassos.webstockmarket.services.AcaoService;


@RestController
@RequestMapping(AcaoController.BASE_URL)
public class AcaoController {

	public static final String BASE_URL = "/api/v1/acoes";

    private final AcaoService acaoService;

    public AcaoController(AcaoService acaoService) {
        this.acaoService = acaoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AcaoDTO> getAll(){
        return acaoService.getAll();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AcaoDTO getById(@PathVariable Long id){
        return acaoService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcaoDTO createNew(@RequestBody AcaoDTO acao){
        return acaoService.createNew(acao);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public AcaoDTO update(@PathVariable Long id, @RequestBody AcaoDTO acao){
        return acaoService.save(id, acao);
    }

}

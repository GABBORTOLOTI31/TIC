package com.ordemservico.controller;

import com.ordemservico.model.Contato;
import com.ordemservico.model.Orcamento;
import com.ordemservico.model.SolicitacaoServico;
import com.ordemservico.service.ContatoService;
import com.ordemservico.service.OrcamentoService;
import com.ordemservico.service.SolicitacaoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private SolicitacaoServicoService solicitacaoServicoService;

    @Autowired
    private ContatoService contatoService;

    @PostMapping("/orcamentos")
    public ResponseEntity<Orcamento> solicitarOrcamento(@RequestBody Orcamento orcamento) {
        Orcamento orcamentoSalvo = orcamentoService.salvar(orcamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(orcamentoSalvo);
    }

    @PostMapping("/solicitacoes")
    public ResponseEntity<SolicitacaoServico> solicitarServico(@RequestBody SolicitacaoServico solicitacao) {
        SolicitacaoServico solicitacaoSalva = solicitacaoServicoService.salvar(solicitacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoSalva);
    }

    @PostMapping("/contatos")
    public ResponseEntity<Contato> enviarContato(@RequestBody Contato contato) {
        Contato contatoSalvo = contatoService.salvar(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoSalvo);
    }
}

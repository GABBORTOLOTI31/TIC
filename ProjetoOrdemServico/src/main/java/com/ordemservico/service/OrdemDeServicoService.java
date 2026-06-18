package com.ordemservico.service;

import com.ordemservico.model.Checklist;
import com.ordemservico.model.ItemMaterial;
import com.ordemservico.model.OrdemDeServico;
import com.ordemservico.model.StatusOS;
import com.ordemservico.repository.OrdemDeServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdemDeServicoService {

    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private EquipamentoService equipamentoService;

    @Autowired
    private MaterialService materialService;

    public OrdemDeServico salvar(OrdemDeServico os) {
        // Valida relacionamentos
        clienteService.buscarPorId(os.getCliente().getId());
        tecnicoService.buscarPorId(os.getTecnico().getId());
        equipamentoService.buscarPorId(os.getEquipamento().getId());

        os.recalcularValorTotal();
        return ordemDeServicoRepository.save(os);
    }

    public List<OrdemDeServico> listarTodas() {
        return ordemDeServicoRepository.findAll();
    }

    public OrdemDeServico buscarPorId(Long id) {
        return ordemDeServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada. ID: " + id));
    }

    public OrdemDeServico atualizarStatus(Long id, StatusOS status) {
        OrdemDeServico os = buscarPorId(id);
        os.setStatus(status);
        if (status == StatusOS.CONCLUIDA) {
            os.setDataConclusao(LocalDateTime.now());
        }
        return ordemDeServicoRepository.save(os);
    }

    public OrdemDeServico adicionarMaterial(Long idOS, Long idMaterial, Double quantidade) {
        OrdemDeServico os = buscarPorId(idOS);
        
        if(os.getStatus() == StatusOS.CONCLUIDA || os.getStatus() == StatusOS.CANCELADA) {
            throw new RuntimeException("Não é possível adicionar materiais a uma OS concluída ou cancelada.");
        }

        var material = materialService.buscarPorId(idMaterial);
        ItemMaterial item = new ItemMaterial(material, quantidade);
        os.adicionarMaterial(item);
        
        return ordemDeServicoRepository.save(os);
    }

    public OrdemDeServico definirChecklist(Long idOS, Checklist checklist) {
        OrdemDeServico os = buscarPorId(idOS);
        os.setChecklist(checklist);
        return ordemDeServicoRepository.save(os);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        ordemDeServicoRepository.deleteById(id);
    }
}

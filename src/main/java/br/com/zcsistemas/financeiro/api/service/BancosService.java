package br.com.zcsistemas.financeiro.api.service;

import br.com.zcsistemas.financeiro.api.model.Bancos;
import br.com.zcsistemas.financeiro.api.repository.BancosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BancosService {

    @Autowired
    private BancosRepository bancosRepository;

    public Bancos atualizar(Long id, Bancos bancos) {
        Bancos bancoSalvo = bancosRepository.findOne(id);
        if (bancoSalvo == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(bancos, bancoSalvo, "id");
        return bancosRepository.save(bancoSalvo);
    }

    private Bancos buscarPeloI(Long id) {
        Bancos bancoSalvo = bancosRepository.findOne(id);
        if (bancoSalvo == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return bancoSalvo;
    }

}

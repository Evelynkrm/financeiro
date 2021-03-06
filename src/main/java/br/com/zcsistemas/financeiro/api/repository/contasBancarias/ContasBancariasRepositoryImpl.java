package br.com.zcsistemas.financeiro.api.repository.contasBancarias;
import br.com.zcsistemas.financeiro.api.model.ContasBancarias;
import br.com.zcsistemas.financeiro.api.model.ContasBancarias_;
import br.com.zcsistemas.financeiro.api.repository.filter.ContasBancariasFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class ContasBancariasRepositoryImpl implements ContasBancariasRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ContasBancarias> filtrar(ContasBancariasFilter contasBancariasFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ContasBancarias> criteria = builder.createQuery(ContasBancarias.class);
        Root<ContasBancarias> root = criteria.from(ContasBancarias.class);

        Predicate[] predicates = criarRestricoes(contasBancariasFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<ContasBancarias> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(contasBancariasFilter));
    }

    private Predicate[] criarRestricoes(ContasBancariasFilter contasBancariasFilter, CriteriaBuilder builder,
                                        Root<ContasBancarias> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(contasBancariasFilter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get(ContasBancarias_.descricao)), "%" + contasBancariasFilter.getDescricao().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<ContasBancarias> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(ContasBancariasFilter contasBancariasFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ContasBancarias> root = criteria.from(ContasBancarias.class);

        Predicate[] predicates = criarRestricoes(contasBancariasFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}

package br.unicesumar.aula.controle;

import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjetoImpl implements ProjetoDAO {
 
    private static List<Projeto> projetos = new ArrayList<>();

    @Override
    public void adicionar(Projeto projeto) {
        projetos.add(projeto);
    } 
    @Override
    public List<Projeto> listar() {
        List<Projeto> projetoList = new ArrayList<>();
        projetoList.addAll(projetos);
        return projetoList;
    } 
    @Override
    public Projeto consultarPorNome(String nome) throws DadoConsultadoException {
        for(Projeto pj:projetos){
            if(pj.getNome().equalsIgnoreCase(nome)){
                return pj;
            }
        }
        throw new DadoConsultadoException("Projeto não encontrado com esse nome: "+nome);
    } 
    @Override
    public Projeto alterar(String nome, Projeto projeto) throws DadoConsultadoException {
        for(Projeto pj:projetos){
            if(pj.getNome().equalsIgnoreCase(nome)){
                Projeto projetoEncontrado=consultarPorNome(nome);
                projetoEncontrado.setNome(projeto.getNome());
                projetoEncontrado.setObjetivo(projeto.getObjetivo());
                projetoEncontrado.setNecessidades(projeto.getNecessidades());
                projetoEncontrado.setDataInicio(projeto.getDataInicio());
                projetoEncontrado.setDataFinal(projeto.getDataFinal());
                projetoEncontrado.setStatus(projeto.getStatus());
                return projetoEncontrado;
            }
        }
        throw new DadoConsultadoException("Projeto não encontrado com este nome: "+nome);
    }
    @Override
    public void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException {
        for(Projeto pj:projetos){
            if(pj.getNome().equalsIgnoreCase(projeto.getNome())){
                projetos.remove(projeto);
                return;
            }
        }
        throw new DadoConsultadoException("Não foi encontrado o projeto para exclusão ");
    } 
    @Override
    public void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException {
        Projeto projeto=consultarPorNome(nome);
        excluir(projeto);
    }
}
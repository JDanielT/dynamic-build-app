package br.com.zonesoftware.dynamic.build.app.controller;

import br.com.zonesoftware.dynamic.build.app.model.Atributo;
import br.com.zonesoftware.dynamic.build.app.model.Classe;
import br.com.zonesoftware.dynamic.build.app.model.TipoAtributo;
import br.com.zonesoftware.dynamic.build.app.repository.ClasseRepository;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author daniel
 */
@Named
@ViewScoped
public class ClasseBean extends AbstractBean<Classe> {

    @Inject
    private ClasseRepository classeRepository;

    @Inject
    private Atributo atributo;

    public ClasseBean() {
        super(Classe.class);
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    @Override
    public ClasseRepository getRepository() {
        return classeRepository;
    }

    @Override
    public void preCadastro() {
        super.preCadastro();
        getEntity().setAtributos(new ArrayList<>());
    }

    /**
     * Métodos relativos a atributos
     */
    public void adicionarAtributo() {
        if (atributo.getRotulo()!= null) {

            //Criando um nome sem caracteres especiais para o atributo
            atributo.setNome(gerarNome(atributo.getRotulo()));
            atributo.setClasse(getEntity());
            
            getEntity().getAtributos().add(atributo);
            atributo = new Atributo();

        }
    }

    public void excluirAtributo(Atributo a) {
        getEntity().getAtributos().remove(a);
        messages.info("Atributo excluído!");
    }

    public TipoAtributo[] getTiposAtributos() {
        return TipoAtributo.values();
    }

    private String gerarNome(String label) {

        label = label.toLowerCase();
        label = Normalizer.normalize(label, Normalizer.Form.NFD);
        label = label.replaceAll("[^\\p{ASCII}]", "");
        label = label.replaceAll("[^a-zZ-Z1-9 ]", "");
        label = label.replaceAll("\\s", "");

        //evitando labels duplicados
        List<Atributo> atributos = getEntity().getAtributos();
        if (atributos != null) {
            label = label + atributos.size();
            List<String> labels = atributos.stream().map(a -> a.getNome()).collect(Collectors.toList());
            if (labels.contains(label)) {
                label = label + new Random().nextInt();
            }
        }

        return label;

    }

}

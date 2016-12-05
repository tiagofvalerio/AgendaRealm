package br.edu.ifspsaocarlos.agenda.data;

import java.util.List;

import br.edu.ifspsaocarlos.agenda.model.Contato;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ContatoDAORealm {

    private Realm realm;

    public ContatoDAORealm(Realm realm) {
        this.realm = realm;
    }


    public OrderedRealmCollection<Contato> buscaContato(String campo) {
        RealmResults<Contato> result;
        if(campo != null) {
            result = realm.where(Contato.class).equalTo("nome", campo).or().
                    equalTo("email", campo).findAll();
        } else {
            result = realm.where(Contato.class).findAll();
        }
        return result;
    }

    public void atualizaContato(final Contato c) {
        final Contato contatoSaved = realm.where(Contato.class).equalTo("id", c.getId()).findFirst();
        if(contatoSaved != null) {
            realm.beginTransaction();
            contatoSaved.setNome(c.getNome());
            contatoSaved.setDataAniversario(c.getDataAniversario());
            contatoSaved.setFoneAdicional(c.getFoneAdicional());
            contatoSaved.setFone(c.getFone());
            contatoSaved.setEmail(c.getEmail());
            realm.commitTransaction();
        }
    }

    public void insereContato(final Contato c) {
        realm.beginTransaction();
        Contato contatoRealm = realm.copyToRealm(c);
        realm.commitTransaction();
    }

    public void apagaContato(final Contato c) {
        final Contato contatoSaved = realm.where(Contato.class).equalTo("id", c.getId()).findFirst();
        if(contatoSaved != null) {
            realm.beginTransaction();
            contatoSaved.deleteFromRealm();
            realm.commitTransaction();
        }
    }

}

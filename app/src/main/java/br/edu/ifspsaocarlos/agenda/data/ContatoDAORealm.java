package br.edu.ifspsaocarlos.agenda.data;

import java.util.List;

import br.edu.ifspsaocarlos.agenda.model.Contato;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ContatoDAORealm {

    public List<Contato> buscaContato(String campo) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Contato> result = realm.where(Contato.class).equalTo("nome", campo).or().
                equalTo("email", campo).findAll();
        realm.close();
        return result;
    }

    public void atualizaContato(final Contato c) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Contato contatoRealm = realm.copyToRealm(c);
            }
        });

        realm.commitTransaction();
        realm.close();
    }

    public void insereContato(final Contato c) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Contato contatoRealm = realm.copyToRealm(c);
            }
        });

        realm.commitTransaction();
        realm.close();
    }

    public void apagaContato(final Contato c) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                c.deleteFromRealm();
            }
        });

        realm.commitTransaction();
        realm.close();
    }

}

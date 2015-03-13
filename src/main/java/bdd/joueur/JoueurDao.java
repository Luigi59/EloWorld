package bdd.joueur;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface JoueurDao {

    @SqlUpdate("create table joueur (id integer primary key autoincrement, prenom text not null, nom text not null, pseudo text not null,"
            + "mdp text not null, email text not null, steamID text, riotID text)")
    void createJoueurTable();

    @SqlUpdate("insert into joueur (prenom, nom, pseudo, mdp, email, steamID, riotID) "
            + "values (:prenom, :nom, :pseudo, :mdp, :email, :steamID, :riotID)")
    @GetGeneratedKeys
    int insert(@Bind("prenom") String prenom, @Bind("nom") String nom, @Bind("pseudo") String pseudo,
            @Bind("mdp") String mdp, @Bind("email") String email, @Bind("steamID") String steamID, @Bind("riotID") String riotID);

    @SqlQuery("select * from joueur where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Joueur findById(@Bind("id") int id);

    @SqlQuery("select * from jeu where pseudo = :pseudo and mdp = :mdp")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Joueur findJoueur(@Bind("pseudo") String pseudo, @Bind("mdp") String mdp);

    @SqlUpdate("drop table if exists joueur")
    void dropJoueurTable();

    void close();
}
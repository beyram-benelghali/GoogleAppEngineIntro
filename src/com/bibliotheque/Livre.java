package com.bibliotheque;


import java.io.IOException;
import java.util.Date;

import com.google.appengine.api.blobstore.BlobKey;
import com.googlecode.objectify.annotation.*;

/* Objectify est une bibliothèque s'occupe de la création d'entités et de clés et de la relation entre ses éléments. 
il utilise automatiquement le service Memcache de Google */

@Entity
@Cache
public class Livre {
	@Id Long id;
	@Index Date date;
	@Index BlobKey key;
	String nom;
	String url ;
	
	//Pour qu'Objectify puisse fonctionner
	//on doit obligatoirement définir un constructeur sans paramètres dans une classe-entité 
	private Livre() {}

    public Livre(BlobKey key, String nom) throws IOException {
    	this.key = key ;
    	this.nom= nom.toUpperCase();
    }
    
    public BlobKey getKey() {
        return key;
    }
    
    public String getKeyString() {
        return key.getKeyString();
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}

// @Cache nous permet d'activer le cache Memcache  pour réduire automatiquement la charge sur le Datastore.
// @Entity on l'ajoute pour que Objectify sache qu'il s'agit d'une classe qu'il peut persister.
// @Index pour faire des requêtes sur ces attributs.
// l'objet BlobKey contient l'id du fichier uploadé par l'utilisateur

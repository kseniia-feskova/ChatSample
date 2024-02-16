package com.data.api

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreService : IFirestoreService {
    private val firestore = FirebaseFirestore.getInstance()

    override fun getCollection(collectionName: String): CollectionReference {
        return firestore.collection(collectionName)
    }

    override fun getDocument(documentPath: String): DocumentReference {
        return firestore.document(documentPath)
    }
}
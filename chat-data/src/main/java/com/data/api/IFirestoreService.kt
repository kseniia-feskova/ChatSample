package com.data.api

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference

interface IFirestoreService {
    fun getCollection(collectionName: String): CollectionReference
    fun getDocument(documentPath: String): DocumentReference
}
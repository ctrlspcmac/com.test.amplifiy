package com.ihh.amplifytest2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.amazonaws.mobile.client.*
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.auth.AuthChannelEventName
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.InitializationStatus
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.generated.model.Todo
import com.amplifyframework.hub.HubChannel
import com.amplifyframework.hub.HubEvent
import com.amplifyframework.storage.StorageAccessLevel
import com.amplifyframework.storage.options.StorageDownloadFileOptions
import com.amplifyframework.storage.options.StorageRemoveOptions
import com.amplifyframework.storage.options.StorageUploadFileOptions
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import java.io.File
import java.io.FileInputStream
import kotlin.coroutines.resume


object Backend {

    private const val TAG = "Backend"

    fun initialize(applicationContext: Context): Backend {
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
 //           Amplify.addPlugin(AWSApiPlugin())
//            Amplify.addPlugin(AWSDataStorePlugin())
//            Amplify.addPlugin(AWSS3StoragePlugin())
            Amplify.configure(applicationContext)
            Log.i(TAG, "Initialized Amplify")
        } catch (e: AmplifyException) {
            Log.e(TAG, "Could not initialize Amplify", e)
        }

        return this
    }


    fun signIn(username: String, password: String) {
        Amplify.Auth.signIn(username, password,
            { result ->
                if (result.isSignInComplete) {
                    Log.i("AUTH", "Sign in succeeded")
                } else {
                    Log.i("AUTH", "Sign in not complete")
                }
            },
            {
                Log.e("AUTH", "Failed to sign in", it)
            }
        )

    }


    fun signOut() {

    }

    fun saveItem() {
        val item = Todo.builder()
            .name("Build iOS application")
            .description("Test 2")
            .id("Task2")
            .build()

        createNote(item)
//        Amplify.DataStore.save(item,
//            { Log.i("Tutorial", "Saved item: ${item.name}") },
//            { Log.e("Tutorial", "Could not save item to DataStore", it) }
//        )

    }

    fun queryNotes() {
        Log.i(TAG, "Querying notes")

        Amplify.API.query(
            ModelQuery.list(Todo::class.java),
            { response ->
                Log.i(TAG, "Queried")
                for (todo in response.data) {
                    Log.i(TAG, todo.name)
                    // TODO should add all the notes at once instead of one by one (each add triggers a UI refresh)
                   // UserData.addNote(UserData.Note.from(noteData))
                }
            },
            { error -> Log.e(TAG, "Query failure", error) }
        )
    }

    fun createNote(todo : Todo) {
        Log.i(TAG, "Creating notes")

        Amplify.API.mutate(
            ModelMutation.create(todo),
            { response ->
                Log.i(TAG, "Created")
                if (response.hasErrors()) {
                    Log.e(TAG, response.errors.first().message)
                } else {
                    Log.i(TAG, "Created Note with id: " + response.data.id)
                }
            },
            { error -> Log.e(TAG, "Create failed", error) }
        )
    }

    fun deleteNote(todo : Todo?) {

        if (todo == null) return

        Log.i(TAG, "Deleting note $todo")

        Amplify.API.mutate(
            ModelMutation.delete(todo),
            { response ->
                Log.i(TAG, "Deleted")
                if (response.hasErrors()) {
                    Log.e(TAG, response.errors.first().message)
                } else {
                    Log.i(TAG, "Deleted Note $response")
                }
            },
            { error -> Log.e(TAG, "Delete failed", error) }
        )
    }

    // storage related
    fun storeImage(filePath: String, key: String) {
        val file = File(filePath)
        val options = StorageUploadFileOptions.builder()
            .accessLevel(StorageAccessLevel.PRIVATE)
            .build()

        Amplify.Storage.uploadFile(
            key,
            file,
            options,
            { progress -> Log.i(TAG, "Fraction completed: ${progress.fractionCompleted}") },
            { result -> Log.i(TAG, "Successfully uploaded: " + result.key) },
            { error -> Log.e(TAG, "Upload failed", error) }
        )
    }

    fun deleteImage(key : String) {

        val options = StorageRemoveOptions.builder()
            .accessLevel(StorageAccessLevel.PRIVATE)
            .build()

        Amplify.Storage.remove(
            key,
            options,
            { result -> Log.i(TAG, "Successfully removed: " + result.key) },
            { error -> Log.e(TAG, "Remove failure", error) }
        )
    }

    fun retrieveImage(key: String, completed : (image: Bitmap) -> Unit) {
        val options = StorageDownloadFileOptions.builder()
            .accessLevel(StorageAccessLevel.PRIVATE)
            .build()

        val file = File.createTempFile("image", ".image")

        Amplify.Storage.downloadFile(
            key,
            file,
            options,
            { progress -> Log.i(TAG, "Fraction completed: ${progress.fractionCompleted}") },
            { result ->
                Log.i(TAG, "Successfully downloaded: ${result.file.name}")
                val imageStream = FileInputStream(file)
                val image = BitmapFactory.decodeStream(imageStream)
                completed(image)
            },
            { error -> Log.e(TAG, "Download Failure", error) }
        )
    }
}
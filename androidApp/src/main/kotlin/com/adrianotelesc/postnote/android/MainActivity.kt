package com.adrianotelesc.postnote.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adrianotelesc.postnote.android.ui.screen.noteeditor.NoteEditorScreen
import com.adrianotelesc.postnote.android.ui.screen.notes.NotesScreen
import com.adrianotelesc.postnote.android.ui.theme.PostnoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            PostnoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "notes",
                    ) {
                        composable(route = "notes") {
                            NotesScreen(
                                navigateToNoteEditor = {
                                    navController.navigate("note_editor/$it")
                                },
                            )
                        }
                        composable(
                            route = "note_editor/{id}",
                            arguments = listOf(
                                navArgument(name = "id") {
                                    type = NavType.StringType
                                    defaultValue = null
                                    nullable = true
                                },
                            )
                        ) { backStackEntry ->
                            NoteEditorScreen(
                                navigateUp = navController::navigateUp,
                                noteId = backStackEntry.arguments?.getString("id"),
                            )
                        }
                    }
                }
            }
        }
    }
}

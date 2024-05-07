//
//  NavController.swift
//  iosApp
//
//  Created by Adriano Teles on 14/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

final class NavController: ObservableObject {
    public enum Destination: Codable, Hashable {
        case noteEditor(noteId: String?)
    }
    
    @Published var path = NavigationPath()
    
    func navigate(to destination: Destination) {
        path.append(destination)
    }
    
    func navigateUp() {
        path.removeLast()
    }
    
    func navigateToRoot() {
        path.removeLast(path.count)
    }
}

//
//  ObservableViewModel.swift
//  iosApp
//
//  Created by Adriano Teles on 14/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Combine
import shared

@MainActor class ObservableViewModel<S: AnyObject, E: AnyObject>: ObservableObject {
    @Published var uiState: S
    private var uiStateCloseable: Closeable?
    
    private var uiEventCloseable: Closeable?
    let uiEffectPublisher = PassthroughSubject<E, Never>()
    
    init(initialUiState: S, viewModel: ViewModel<S, E>) {
        self.uiState = initialUiState

        uiStateCloseable = viewModel.uiStateAsCFlow().watch { [weak self] uiState in
            self?.uiState = uiState
        }
        uiEventCloseable = viewModel.uiEffectAsCFlow().watch { [weak self] uiEffect in
            self?.uiEffectPublisher.send(uiEffect)
        }
    }
    
    deinit {
        uiStateCloseable?.close()
        uiEventCloseable?.close()
    }
}

import Foundation
import SharedLogic

// MARK: - Shared ViewModel bridge

@MainActor
final class ArticlesStore: ObservableObject {
    @Published var state = ArticlesContract.State(
        isLoading: false,
        articles: [],
        errorMessage: nil
    )

    private let viewModel: ArticlesViewModel
    private var stateObservation: StateObservation?

    init() {
        let viewModel = ArticlesInjector().articlesViewModel
        self.viewModel = viewModel

        stateObservation = viewModel.observeState { [weak self] state in
            DispatchQueue.main.async {
                self?.state = state
            }
        }
    }

    func loadArticles() {
        viewModel.onIntent(intent: ArticlesContractIntentLoadArticles.shared)
    }

    func toggleFavorite(_ article: Article) {
        viewModel.onIntent(
            intent: ArticlesContractIntentToggleFavorite(article: article)
        )
    }

    deinit {
        stateObservation?.cancel()
    }
}

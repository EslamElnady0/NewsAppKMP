import SwiftUI
import SharedLogic

struct ContentView: View {
    @StateObject private var store = ArticlesStore()

    var body: some View {
        NavigationStack {
            ZStack {
                Color.newsCanvas.ignoresSafeArea()

                if store.state.isLoading && store.state.articles.isEmpty {
                    LoadingFeed()
                } else if let message = store.state.errorMessage,
                          store.state.articles.isEmpty {
                    ErrorView(message: message) {
                        store.loadArticles()
                    }
                } else {
                    ArticlesList(
                        articles: store.state.articles,
                        onToggleFavorite: store.toggleFavorite
                    )
                }
            }
            .navigationTitle("Articles")
            .navigationBarTitleDisplayMode(.large)
        }
        .task {
            store.loadArticles()
        }
    }
}

#Preview {
    ContentView()
}

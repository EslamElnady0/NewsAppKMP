import SwiftUI
import SharedLogic

// MARK: - Articles list

struct ArticlesList: View {
    let articles: [Article]
    let onToggleFavorite: (Article) -> Void

    var body: some View {
        ScrollView {
            LazyVStack(spacing: 18) {
                ForEach(Array(articles.enumerated()), id: \.element.id) { index, article in
                    AnimatedArticleRow(
                        article: article,
                        index: index,
                        onToggleFavorite: onToggleFavorite
                    )
                }
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 18)
        }
    }
}

private struct AnimatedArticleRow: View {
    let article: Article
    let index: Int
    let onToggleFavorite: (Article) -> Void
    @State private var isVisible = false

    var body: some View {
        ArticleCard(article: article, onToggleFavorite: onToggleFavorite)
            .opacity(isVisible ? 1 : 0)
            .offset(y: isVisible ? 0 : 28)
            .scaleEffect(isVisible ? 1 : 0.97)
            .onAppear {
                withAnimation(
                    .spring(response: 0.58, dampingFraction: 0.82)
                    .delay(Double(min(index, 6)) * 0.07)
                ) {
                    isVisible = true
                }
            }
    }
}

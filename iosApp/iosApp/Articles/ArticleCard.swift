import SwiftUI
import SharedLogic

// MARK: - Article card

struct ArticleCard: View {
    let article: Article
    let onToggleFavorite: (Article) -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            AsyncImage(
                url: URL(string: article.imageUrl),
                transaction: Transaction(animation: .easeOut(duration: 0.35))
            ) { phase in
                switch phase {
                case .success(let image):
                    image
                        .resizable()
                        .scaledToFill()
                        .transition(.opacity)
                case .failure:
                    ZStack {
                        Color.gray.opacity(0.16)
                        Image(systemName: "photo")
                            .font(.system(size: 30))
                            .foregroundStyle(.secondary)
                    }
                default:
                    ZStack {
                        Color.gray.opacity(0.13)
                        ProgressView().tint(.newsAccent)
                    }
                }
            }
            .frame(height: 190)
            .frame(maxWidth: .infinity)
            .clipped()
            .overlay(alignment: .topTrailing) {
                Button {
                    onToggleFavorite(article)
                } label: {
                    Image(systemName: article.isFavorite ? "heart.fill" : "heart")
                        .font(.system(size: 19, weight: .semibold))
                        .foregroundStyle(Color.newsAccent)
                        .frame(width: 44, height: 44)
                        .background(.white.opacity(0.94), in: Circle())
                        .shadow(color: .black.opacity(0.12), radius: 4, y: 2)
                }
                .accessibilityLabel(
                    article.isFavorite ? "Remove from favorites" : "Add to favorites"
                )
                .padding(12)
            }

            VStack(alignment: .leading, spacing: 10) {
                Text("LATEST STORY")
                    .font(.caption2.weight(.heavy))
                    .tracking(1.3)
                    .foregroundStyle(Color.newsAccent)

                Text(article.title)
                    .font(.title3.weight(.bold))
                    .foregroundStyle(Color.newsInk)
                    .lineLimit(3)

                Text(article.description)
                    .font(.subheadline)
                    .foregroundStyle(Color.newsMuted)
                    .lineSpacing(4)
                    .lineLimit(3)

                Divider().padding(.top, 4)

                HStack(spacing: 10) {
                    Text(String(article.author.prefix(1)))
                        .font(.caption.weight(.bold))
                        .foregroundStyle(.white)
                        .frame(width: 34, height: 34)
                        .background(Color.newsInk, in: Circle())

                    VStack(alignment: .leading, spacing: 2) {
                        Text(article.author)
                            .font(.caption.weight(.semibold))
                            .foregroundStyle(Color.newsInk)
                        Text("News correspondent")
                            .font(.caption2)
                            .foregroundStyle(Color.newsMuted)
                    }
                }
            }
            .padding(18)
        }
        .background(.white)
        .clipShape(RoundedRectangle(cornerRadius: 22, style: .continuous))
        .shadow(color: .black.opacity(0.08), radius: 12, y: 5)
    }
}

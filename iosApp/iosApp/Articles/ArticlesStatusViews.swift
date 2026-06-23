import SwiftUI

// MARK: - Loading and error states

struct LoadingFeed: View {
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 18) {
                ForEach(0..<3, id: \.self) { _ in
                    LoadingCard()
                }
            }
            .padding(16)
        }
    }
}

private struct LoadingCard: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 14) {
            Rectangle()
                .fill(Color.gray.opacity(0.18))
                .frame(height: 190)
            RoundedRectangle(cornerRadius: 7)
                .fill(Color.gray.opacity(0.18))
                .frame(height: 22)
                .padding(.horizontal, 18)
            RoundedRectangle(cornerRadius: 7)
                .fill(Color.gray.opacity(0.18))
                .frame(width: 210, height: 14)
                .padding(.horizontal, 18)
            Spacer().frame(height: 8)
        }
        .background(.white)
        .clipShape(RoundedRectangle(cornerRadius: 22, style: .continuous))
        .modifier(ShimmerEffect())
    }
}

private struct ShimmerEffect: ViewModifier {
    @State private var phase: CGFloat = -1

    func body(content: Content) -> some View {
        content
            .overlay {
                GeometryReader { geometry in
                    LinearGradient(
                        colors: [.clear, .white.opacity(0.55), .clear],
                        startPoint: .top,
                        endPoint: .bottom
                    )
                    .rotationEffect(.degrees(22))
                    .offset(x: phase * geometry.size.width * 1.8)
                }
                .clipped()
                .allowsHitTesting(false)
            }
            .onAppear {
                withAnimation(.linear(duration: 1.25).repeatForever(autoreverses: false)) {
                    phase = 1
                }
            }
    }
}

struct ErrorView: View {
    let message: String
    let retry: () -> Void

    var body: some View {
        VStack(spacing: 14) {
            Image(systemName: "newspaper")
                .font(.system(size: 42))
                .foregroundStyle(Color.newsAccent)
            Text("Couldn’t load the news")
                .font(.title3.bold())
            Text(message)
                .font(.subheadline)
                .foregroundStyle(.secondary)
            Button("Try again", action: retry)
                .buttonStyle(.borderedProminent)
                .tint(.newsAccent)
        }
        .multilineTextAlignment(.center)
        .padding(32)
    }
}

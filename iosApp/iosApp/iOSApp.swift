import SwiftUI
import SharedLogic

@main
struct iOSApp: App {
    init() {
        KoinInitializer().initialize()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

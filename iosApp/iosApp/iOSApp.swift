import SwiftUI
import SharedLogic

@main
struct iOSApp: App {
    init() {
        let database = IosDatabaseFactory().createDatabase()
        KoinInitializer().initialize(database: database)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

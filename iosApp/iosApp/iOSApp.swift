// xcode: set sdk=iOS

import SwiftUI
import Shared

@main
struct iOSApp: App {
    init(){
       StartFunctionKt.doInitKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()

        }
    }
}

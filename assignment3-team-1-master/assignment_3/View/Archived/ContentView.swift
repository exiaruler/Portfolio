//
//  ContentView.swift
//  assignment_3
//
//  Created by Samuel Li on 5/5/21.
//

import SwiftUI

struct ContentView: View {
   
    var body: some View {
        NavigationView {
            VStack {
                //("Test")
                MainView()
            }
        }
        //.navigationBarTitle("Test")
    }
}

// preview
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

//
//  MainView.swift
//  assignment_3
//
//  Created by user924970 on 5/8/21.
//

import SwiftUI

struct MainView: View {
    @StateObject var foodModel = FoodViewFunctions()
    var body: some View {
        TabView {
            Home()
                .environmentObject(foodModel)
            .tabItem {
                Label("Home", systemImage: "house");
            }
            PersonalView().environmentObject(foodModel)
            .tabItem {
                Label("Personal", systemImage: "person")
            }
            //Placeholder
            HistoryView()
                .environmentObject(foodModel)
                .tabItem {
                    Label("History", systemImage: "calendar.badge.clock")
                }
        }
        .accentColor(.green)
        .edgesIgnoringSafeArea(.top)
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}

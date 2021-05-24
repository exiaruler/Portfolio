//
//  Home.swift
//  assignment_3
//
//  Created by Samuel Li on 6/5/21.
//

import SwiftUI

struct Home: View {
    @State var activeSheet: ActiveSheet?
    //Sheet 1 - Set Target Popup
    //Sheet 2 - Add Food Popup
    
    //Popup states
    @State private var showFoodPopup = false;
    @State private var showTargetPopup = false;

    @EnvironmentObject var foodModel : FoodViewFunctions
    
    var body: some View {
        ZStack {
            VStack {
                //Title
                HStack {
                    Text("Home")
                        .font(.largeTitle)
                        .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                        .foregroundColor(.green)
                }
                //Welcome text
                HStack {
                    Text("Welcome User")
                        .font(.title2)
                        .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                        .multilineTextAlignment(/*@START_MENU_TOKEN@*/.leading/*@END_MENU_TOKEN@*/)
                    Spacer()
                }
                .padding()
                //Daily Calories text
                HStack {
                    Text("\(foodModel.totalCalories) out of \(foodModel.targetCalories) calories today")
                }
                //Set target calories button
                HStack {
                    Button("Set Target") {
                        action: do {activeSheet = .first}
                        showTargetPopup.toggle()
                    }
                    .buttonStyle(StandardFilledButton())
                }
                Spacer()
                //Add Food button
                HStack {
                    Button("Add Food") {
                        action: do {activeSheet = .second}
                        showFoodPopup.toggle()
                    }
                    .buttonStyle(StandardFilledButton())
                    Spacer()
                }
                .padding(.leading, UIScreen.screenWidth * 0.1)
                //TableView
                FoodTable().environmentObject(foodModel)
            }
            
            //Switch active sheet based on button pressed
            .sheet(item: $activeSheet) { item in
                switch item {
                case .first:
                    SetTargetPopup(show: $showTargetPopup).environmentObject(foodModel)
                case .second:
                    AddFoodPopup(show: $showFoodPopup).environmentObject(foodModel)
                }
            }
        }
    }
}

//Sheets enumerator
enum ActiveSheet: Identifiable {
    case first, second
    
    var id: Int {
        hashValue
    }
}


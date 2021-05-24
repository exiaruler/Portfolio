//
//  HistoryTable.swift
//  assignment_3
//
//  Created by user924970 on 5/18/21.
//

import SwiftUI

struct HistoryTable: View {
    @EnvironmentObject var foods : FoodViewFunctions
    
    var body: some View {
        VStack(spacing: 10) {
            //Title
            HStack {
                Text("Intake History")
                    .font(.title2)
                    .fontWeight(.bold)
                    .foregroundColor(.green)
            }
            .padding()
            //Subheading - target calories
            HStack {
                Text("Target: \(foods.targetCalories)")
                    .font(.title3)
                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                    .foregroundColor(.red)
            }
            HStack (spacing: 50) {
                VStack { //Column 1 Heading
                    Text("Date")
                        .fontWeight(.bold)
                }
                .frame(minWidth: UIScreen.screenWidth * 0.25, maxWidth: UIScreen.screenWidth * 0.25)
                VStack { //Column 2 Heading
                    Text("Calorie Intake")
                        .fontWeight(.bold)
                }
                .frame(minWidth: UIScreen.screenWidth * 0.25, maxWidth: UIScreen.screenWidth * 0.25)
                VStack { //Column 3 Heading
                    Text("Excess")
                        .fontWeight(.bold)
                }
                .frame(minWidth: UIScreen.screenWidth * 0.25, maxWidth: UIScreen.screenWidth * 0.25)
            }
            .padding()
            .border(Color.green)
            //History list
            List {
                ForEach(self.foods.history) {day in
                    HStack(spacing: 30) {
                        VStack { //Column 1: Date
                            Text(day.date)
                        }
                        .frame(minWidth: UIScreen.screenWidth * 0.25, maxWidth: UIScreen.screenWidth * 0.25, minHeight: UIScreen.screenWidth * 0.1)
                        VStack { //Column 2: Calorie Intake
                            Text(day.totalCalories)
                        }
                        .frame(minWidth: UIScreen.screenWidth * 0.25, maxWidth: UIScreen.screenWidth * 0.25, minHeight: UIScreen.screenWidth * 0.1)
                        VStack { //Column 3: Excess
                            Text(day.remainder)
                                .offset(x: UIScreen.screenWidth * 0.03)
                        }
                        .frame(minWidth: UIScreen.screenWidth * 0.25, maxWidth: UIScreen.screenWidth * 0.25, minHeight: UIScreen.screenWidth * 0.1)
                    }
                    .offset(x: -UIScreen.screenWidth * 0.05)
                }
            }
        }
        .border(Color.green, width: 2)
        .frame(maxWidth: UIScreen.screenWidth * 0.9, maxHeight: UIScreen.screenHeight * 0.8)
        
    }
}


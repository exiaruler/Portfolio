//
//  PersonalView.swift
//  assignment_3
//
//  Created by user182748 on 5/11/21.
//

import Charts
import Foundation
import SwiftUI

struct PersonalView: View {
    @EnvironmentObject var foodModel : FoodViewFunctions
    @State private var pieChartEntries: [PieChartDataEntry] = []
    
    var totalCalories = 2500;
    @State var quote = "Weight loss = Calorie deficit"
    @State private var counter = 0;
    let timer = Timer.publish(every: 5, on: .current, in: .common).autoconnect()
    
    var body: some View {
        VStack(alignment: .center) {
            //Title
            HStack {
                Text("Personal")
                    .font(.largeTitle)
                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                    .foregroundColor(.green)
            }
            //Headings
            HStack {
                Text("Goal")
                    .font(.title2)
                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                    .padding(.leading, UIScreen.screenWidth * 0.05)
                    Spacer()
                Text("Current")
                    .font(.title2)
                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                    Spacer()
                Text("Remaining")
                    .font(.title2)
                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                    .padding(.trailing, UIScreen.screenWidth * 0.05)
            }
            
            //Information
            HStack {
                Text("\(foodModel.targetCalories)")
                    .font(.title2)
                    .padding(.leading, UIScreen.screenWidth * 0.05)
                    Spacer()
                Text("\(foodModel.totalCalories)")
                    .font(.title2)
                    .offset(x: -UIScreen.screenWidth * 0.05)
                    Spacer()
                Text("\(Int(foodModel.targetCalories)! - foodModel.totalCalories)")
                    .font(.title2)
                    .padding(.trailing, UIScreen.screenWidth * 0.05)
            }
//            .font(.subheadline)
//            .padding()
            
            Spacer()
                .frame(idealHeight:  100)
                .fixedSize()
            
            //Pie Chart
            VStack {
                Text("Food Eaten Today")
                    .font(.title2)
                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                    .multilineTextAlignment(.center)
            
                //PieChart(storedFoods: foodModel)
                PieChart(entries: entriesForFood(foods: foodModel.foods))
                
                    //.environmentObject(foodModelState)
                    .frame(width: 350, height: 350)
            }
            Spacer()
                
            HStack {
                Text("\(quote)")
                    .onReceive(timer) { _ in
                        print(counter)
                        let randomInt = Int.random(in: 1...6)
                        
                        if randomInt == 1 {
                            quote = "Don't SKIP meals"
                        }
                        
                        if randomInt == 2 {
                            quote = "Don't LOSE weight too quickly"
                        }
                        
                        if randomInt == 3 {
                            quote = "Avoid diets. They only work short-term"
                        }
                        
                        if randomInt == 4 {
                            quote = "GET ENOUGH SLEEP"
                        }
                        
                        if randomInt == 5 {
                            quote = "Weight loss = Calorie deficit"
                        }
                        
                        if randomInt == 6 {
                            quote = "Eat LOW calorie dense foods"
                        }
                        
                        if counter == 30 {
                            self.timer.upstream.connect().cancel()
                        }
                        self.counter += 1
                    }
                    .font(.title2)
                    .foregroundColor(.green)
                    .multilineTextAlignment(.center)
                    
                }
            Spacer()
            }
            
        }
    }
//struct PersonalView_Previews: PreviewProvider {
//    static var previews: some View {
//        PersonalView()
//    }
//}

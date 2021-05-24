//
//  SetTargetPopup.swift
//  assignment_3
//
//  Created by user924970 on 5/18/21.
//

import SwiftUI
import Combine

struct SetTargetPopup: View {
    @Binding var show : Bool
    @State var targetCalories : String = ""
    
    @EnvironmentObject var foodModel: FoodViewFunctions
    @Environment(\.presentationMode) var presentation
    
    var body: some View {
        ZStack {
            if show {
                //Darken background while popup is active
                Color.black.opacity(show ? 0.3 : 0).edgesIgnoringSafeArea(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
                
                //Title
                VStack(alignment: .center, spacing: 0) {
                    Text("Set Target")
                        .fontWeight(.bold)
                        .foregroundColor(.green)
                    
                    VStack {
                        //Set target calories textfield
                        HStack {
                            Text("Target Calories: ")
                                .frame(maxWidth: 100, alignment: .leading)
                            TextField("Insert a number above 0", text: $targetCalories)
                                .keyboardType(.numberPad) //Prevent non-numeric input
                                .onReceive(Just(targetCalories)) { value in
                                    let filtered = value.filter {"0123456789".contains($0)}
                                    if filtered != value {
                                        targetCalories = filtered
                                    }
                                }
                        }
                    }
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding()
                    
                    
                    HStack (spacing: 50) {
                        //Dismiss View
                        Button("Dismiss") {
                            show = false
                            presentation.wrappedValue.dismiss()
                        }
                        //Set Target
                        Button("Set") {
                            if (targetCalories != "") {
                                action:do {
                                    foodModel.setTarget(presentation: presentation, target: targetCalories)
                                }
                            }
                                show = false
                            }
                        }
                        .buttonStyle(SmallOutlineButton())
 
                    }
                    .padding()
                    .frame(maxWidth: 400)
                    .border(Color.green, width: 1)
                    .background(Color.white)
                }
            }
    }
}


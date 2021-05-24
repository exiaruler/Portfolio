//
//  AddFoodPopup.swift
//  assignment_3
//
//  Created by user924970 on 5/8/21.
//

import SwiftUI
import Combine
struct AddFoodPopup: View {
    @Binding var show : Bool
    
    @State private var foodName : String = ""
    @State private var calories : String = ""
    @State var selectedDate = Date()
    // view model
    @EnvironmentObject var foodModel: FoodViewFunctions
    @Environment(\.presentationMode) var presentation
    
    var body: some View {
        ZStack {
            if show {
                
                Color.black.opacity(show ? 0.3 : 0).edgesIgnoringSafeArea(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
                
                //Title
                VStack(alignment: .center, spacing: 0) {
                    Text("Add Food")
                        .fontWeight(.bold)
                        .foregroundColor(.green)
                    
                    VStack {
                        //Set foodname textfield
                        HStack {
                            Text("Food Name: ")
                                .frame(maxWidth: 100, alignment: .leading)
                            TextField("Insert Food Name", text:$foodModel.foodName)
                        }
                        //Set calories textfield
                        HStack {
                            Text("Calories: ")
                                .frame(maxWidth: 100, alignment: .leading)
                            TextField("Insert a value greater than 0", text: $foodModel.calrious)
                                .keyboardType(.numberPad) //Prevent non-numeric input
                                .onReceive(Just(foodModel.calrious)) { value in
                                    let filtered = value.filter {"0123456789".contains($0) }
                                    if filtered != value {
                                        foodModel.calrious = filtered
                                    }
                                }
                        }
                        //Set servings textfield
                        HStack {
                            Text("Servings: ")
                                .frame(maxWidth: 100, alignment: .leading)
                            TextField("Servings you had", text: $foodModel.servings).keyboardType(.numberPad)
                        }
                        //Set date
                        HStack {
                            Text("Date: ")
                                .frame(maxWidth: 100, alignment: .leading)
                            DatePicker("", selection: $foodModel.date, displayedComponents: .date)
                                .offset(x: -UIScreen.screenWidth * 0.25)
                        }
                    }
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding()
                    
                    HStack (spacing: 50) {
                        //Dismiss view button
                        Button("Dismiss") {
                            show = false
                            presentation.wrappedValue.dismiss()
                        }
                        .buttonStyle(SmallOutlineButton())
                        //Add food to database button
                        Button("Add") {
                            if (foodModel.foodName != "" && foodModel.calrious != "") {
                                action:do {
                                    foodModel.addManual(presentation:presentation)
                                }
                                show = false
                            }
                        }
                        .buttonStyle(SmallOutlineButton())
 
                    }
                    .padding()
                }
                .padding()
                .frame(maxWidth: 400)
                .border(Color.green, width: 1)
                .background(Color.white)
            }
        }
        .onTapGesture {
            self.hideKeyboard()
        }
    }
}

//Dismisses keyboard if user taps outside of textfield
#if canImport(UIKit)
extension View {
    func hideKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}
#endif

//Remove time from date
extension Date {
    public var removeTimeStamp : Date? {
        guard let date = Calendar.current.date(from: Calendar.current.dateComponents([.year, .month, .day], from: self)) else {
            return nil
        }
        return date
    }
}

//struct AddFoodPopup_Previews: PreviewProvider {
//    static var previews: some View {
//        AddFoodPopup(show: )
//    }
//}

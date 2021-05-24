//
//  FoodTable.swift
//  assignment_3
//
//  Created by user924970 on 5/15/21.
//

import SwiftUI
import RealmSwift

struct FoodTable: View {
    @EnvironmentObject var foods : FoodViewFunctions
    
    var body: some View {
        VStack(spacing: 10) {
            //Title
            HStack {
                Text("Today's Intake")
                    .font(.title2)
                    .fontWeight(.bold)
                    .foregroundColor(.green)
            }
            .padding()
            //Column Headings
            HStack (spacing: 25) {
                VStack { //Column 1 Heading
                    Text("Food Name")
                        .fontWeight(.bold)
                }
                .frame(minWidth: UIScreen.screenWidth * 0.3, maxWidth: UIScreen.screenWidth * 0.3)
                VStack { //Column 2 Heading
                    Text("Food Calories")
                        .fontWeight(.bold)
                }
                .frame(minWidth: UIScreen.screenWidth * 0.25, maxWidth: UIScreen.screenWidth * 0.25)
                VStack { //Column 3 Heading
                    Text("Servings")
                        .fontWeight(.bold)
                }
                .frame(minWidth: UIScreen.screenWidth * 0.25, maxWidth: UIScreen.screenWidth * 0.25)
            }
            .padding()
            .border(Color.green)
            List {
                ForEach(self.foods.foods) {food in
                    HStack (spacing: 25){
                        VStack { //Column 1 : Food Name
                            Text(food.foodName);
                        }
                        .frame(minWidth: UIScreen.screenWidth * 0.3, maxWidth: UIScreen.screenWidth * 0.3, minHeight: UIScreen.screenHeight * 0.05)
                        VStack { //Column 2 : Food Calories
                            Text(food.calrious)
                        }
                        .frame(minWidth: UIScreen.screenWidth * 0.25, maxWidth: UIScreen.screenWidth * 0.25, minHeight: UIScreen.screenHeight * 0.05)
                        VStack { //Column 3 : Servings
                            Text(food.servings)
                        }
                        .frame(minWidth: UIScreen.screenWidth * 0.2, maxWidth: UIScreen.screenWidth * 0.2, minHeight: UIScreen.screenHeight * 0.05)
                    }
                    .offset(x: -UIScreen.screenWidth * 0.08)
                }
                .onDelete(perform: Delete)
            }
        }
        .border(Color.green, width: 2)
        .frame(maxWidth: UIScreen.screenWidth * 0.75, maxHeight: UIScreen.screenHeight * 0.45)
        
    }
    //Delete Food from Realm database
    func Delete(at offsets: IndexSet) {
        offsets.forEach ({ index in
            self.foods.deleteFood(entry: self.foods.foods[index])
        })
    }
    
    
}

//Store screen dimensions
extension UIScreen {
    static let screenWidth = UIScreen.main.bounds.size.width;
    static let screenHeight = UIScreen.main.bounds.size.height;
    static let screenSize = UIScreen.main.bounds.size;
}

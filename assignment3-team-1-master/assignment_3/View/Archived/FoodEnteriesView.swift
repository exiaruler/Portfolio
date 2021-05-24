//
//  FoodEnteriesView.swift
//  assignment_3
//
//  Created by Samuel Li on 13/5/21.
//

import SwiftUI

struct FoodEnteriesView: View {
    @StateObject var foodModel = FoodViewFunctions()
    var body: some View {
        
        NavigationView{
            ScrollView{
                VStack(spacing: 20){
                    ForEach(foodModel.foods){food in
                    Text(food.foodName)
                    Text(food.calrious)
                    }
                }
 
            }
            .navigationTitle("Food Items")
          
          

        }
         
    }

   
}

struct FoodEnteriesView_Previews: PreviewProvider {
    static var previews: some View {
        FoodEnteriesView()
    }
}

//
//  History.swift
//  assignment_3
//
//  Created by user924970 on 5/18/21.
//

import SwiftUI

struct History: View {
    @EnvironmentObject var foods : FoodViewFunctions
    
    var body: some View {
        VStack {
            //Title
            HStack {
                Text("History")
                    .font(.largeTitle)
                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                    .foregroundColor(.green)
            }
            Spacer()
            //TableView
            HistoryTable().environmentObject(foods)
        }
    }
}

//struct History_Previews: PreviewProvider {
//    static var previews: some View {
//        History()
//    }
//}

package com.mahmoudbashir.applyingcomposefeatures

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun DropMenuSelectionContent() {
    val selectedItems = remember { mutableStateListOf<String>("Item 3","Item 2") }
    val expanded = remember { mutableStateOf(false) }

    MultipleSelectionDesign(selectedItems,expanded)
}


@Composable
fun MultipleSelectionDesign(selectedItems:SnapshotStateList<String>,expanded:MutableState<Boolean>) {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4","Item 5","Item 6","Item 7","Item 8")


    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 0.dp)
    ) {
        SelectionLazyRowMainContent(selectedItems,expanded)
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }) {
            items.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item)

                    Checkbox(
                        checked = selectedItems.contains(item),
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                selectedItems.add(item)
                            } else {
                                selectedItems.remove(item)
                            }
                        }
                    )
                }

            }
        }
    }

}

@Composable
fun SelectionMainContent(selectedItems:SnapshotStateList<String>,expanded:MutableState<Boolean>){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .border(border = BorderStroke(1.dp, Color.Blue), RoundedCornerShape(8.dp))
            .clickable(
                onClick = {
                    expanded.value = true
                }
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (selectedItems.isEmpty()) "Select items" else
                    selectedItems.joinToString(),
            )
            Icon(imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp
            else Icons.Default.ArrowDropDown, contentDescription = null)
        }

    }
}

@Composable
fun SelectionLazyRowMainContent(selectedItems:SnapshotStateList<String>,expanded:MutableState<Boolean>){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .border(border = BorderStroke(1.dp, Color.Blue), RoundedCornerShape(8.dp))
            .clickable(
                onClick = {
                    expanded.value = true
                }
            ),
    ) {
        Icon(modifier =Modifier.align(Alignment.CenterEnd),imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp
        else Icons.Default.ArrowDropDown, contentDescription = null)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(horizontal = 5.dp),
        ) {
            items(selectedItems){
                item->
                Box(modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Black, RoundedCornerShape(8.dp))
                    .padding(3.dp)){
                    Text(
                        text = if (selectedItems.isEmpty()) "Select items" else
                            item,
                        style = TextStyle(
                            color = Color.Red
                        )
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))

            }
        }

    }
}
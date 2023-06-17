import pandas as pd

# Read the CSV files
file1 = pd.read_csv('quantium-starter-repo\data\daily_sales_data_0.csv')
file2 = pd.read_csv('quantium-starter-repo\data\daily_sales_data_1.csv')
file3 = pd.read_csv('quantium-starter-repo\data\daily_sales_data_2.csv')

# Filter rows with only Pink Morsels in the "product" field
file1 = file1[file1['product'] == 'Pink Morsels']
file2 = file2[file2['product'] == 'Pink Morsels']
file3 = file3[file3['product'] == 'Pink Morsels']

# Multiply the "quantity" and "price" columns to get the "sales" column
file1['sales'] = file1['quantity'] * file1['price']
file2['sales'] = file2['quantity'] * file2['price']
file3['sales'] = file3['quantity'] * file3['price']

# Select the desired fields: "sales", "date", and "region"
output = pd.concat([file1[['sales', 'date', 'region']],
                    file2[['sales', 'date', 'region']],
                    file3[['sales', 'date', 'region']]])

# Write the output to a new CSV file
output.to_csv('formatted_output.csv', index=False)

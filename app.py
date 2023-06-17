import pandas as pd
import dash
import dash_core_components as dcc
import dash_html_components as html
from dash.dependencies import Input, Output

df = pd.read_csv('formatted_output.csv')
app = dash.Dash(__name__)
app.layout = html.Div(
    children=[
        html.H1("Sales Visualizer"),
        dcc.Graph(
            id="sales-chart",
            figure={
                "data": [
                    {"x": df["date"], "y": df["sales"], "type": "line", "name": "Sales"},
                ],
                "layout": {
                    "title": "Sales Data",
                    "xaxis": {"title": "Date"},
                    "yaxis": {"title": "Sales"},
                },
            },
        ),
    ]
)
@app.callback(
    Output("sales-chart", "figure"),
    [Input("date-range", "start_date"), Input("date-range", "end_date")]
)
def update_chart(start_date, end_date):
    filtered_df = df[(df["date"] >= start_date) & (df["date"] <= end_date)]
    figure = {
        "data": [
            {"x": filtered_df["date"], "y": filtered_df["sales"], "type": "line", "name": "Sales"},
        ],
        "layout": {
            "title": "Sales Data",
            "xaxis": {"title": "Date"},
            "yaxis": {"title": "Sales"},
        },
    }
    return figure
if __name__ == "__main__":
    app.run_server(debug=True)

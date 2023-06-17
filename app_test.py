import unittest
from app import app


class TestSalesVisualizer(unittest.TestCase):

    def setUp(self):
        self.app = app.test_client()
        self.app.testing = True

    def test_home_page(self):
        response = self.app.get("/")
        self.assertEqual(response.status_code, 200)
        self.assertIn(b"Sales Visualizer", response.data)

    def test_default_region_filter(self):
        response = self.app.get("/")
        self.assertEqual(response.status_code, 200)
        self.assertIn(b'checked="checked" value="All"', response.data)

    def test_region_filter(self):
        regions = ["North", "East", "South", "West"]
        for region in regions:
            response = self.app.get(f"/?region-filter={region}")
            self.assertEqual(response.status_code, 200)
            self.assertIn(f'checked="checked" value="{region}"', response.data)

    def test_chart_update(self):
        response = self.app.get("/?region-filter=North")
        self.assertEqual(response.status_code, 200)
        self.assertIn(b"Sales Data", response.data)
        self.assertIn(b'"x": [', response.data)
        self.assertIn(b'"y": [', response.data)

if __name__ == "__main__":
    unittest.main()

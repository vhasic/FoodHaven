import {render, screen} from "@testing-library/react";
import Home from "../pages/Home";

describe('Home', function () {
    it('Test renders correctly', function () {
        render(<Home/>);
        expect(screen.getByRole('heading', {
            name: /foodhaven/i
        })).toHaveTextContent("FoodHaven");
    });
});
import { render, screen } from '@testing-library/react';
import BookList from 'components/pages/books/BookList';

test('renders learn react link', () => {
  render(<BookList />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});

using DAL.Entities;
using DAL.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BLL.Service
{
    public class VenueService
    {
        private VenueRepo _venueRepo;
        public VenueService()
        {
            _venueRepo = new VenueRepo();
        }

        // ham lay het thong tin bang Venue
        public List<Venue> GetVenues()
        {
            return _venueRepo.GetVenues();
        }
    }
}
